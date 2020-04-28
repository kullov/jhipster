import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './request-assignment.reducer';
import { IRequestAssignment } from 'app/shared/model/request-assignment.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IRequestAssignmentDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const RequestAssignmentDetail = (props: IRequestAssignmentDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { requestAssignmentEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          RequestAssignment [<b>{requestAssignmentEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="startDate">Start Date</span>
          </dt>
          <dd>
            <TextFormat value={requestAssignmentEntity.startDate} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="endDate">End Date</span>
          </dt>
          <dd>
            <TextFormat value={requestAssignmentEntity.endDate} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="dateCreated">Date Created</span>
          </dt>
          <dd>
            <TextFormat value={requestAssignmentEntity.dateCreated} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>Intern Request Assignment</dt>
          <dd>{requestAssignmentEntity.internRequestAssignmentCode ? requestAssignmentEntity.internRequestAssignmentCode : ''}</dd>
          <dt>Organization Request Assignment</dt>
          <dd>
            {requestAssignmentEntity.organizationRequestAssignmentName ? requestAssignmentEntity.organizationRequestAssignmentName : ''}
          </dd>
          <dt>Status</dt>
          <dd>{requestAssignmentEntity.statusName ? requestAssignmentEntity.statusName : ''}</dd>
        </dl>
        <Button tag={Link} to="/request-assignment" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/request-assignment/${requestAssignmentEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ requestAssignment }: IRootState) => ({
  requestAssignmentEntity: requestAssignment.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(RequestAssignmentDetail);
