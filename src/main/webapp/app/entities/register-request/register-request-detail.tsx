import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './register-request.reducer';
import { IRegisterRequest } from 'app/shared/model/register-request.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IRegisterRequestDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const RegisterRequestDetail = (props: IRegisterRequestDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { registerRequestEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          RegisterRequest [<b>{registerRequestEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="dateCreated">Date Created</span>
          </dt>
          <dd>
            <TextFormat value={registerRequestEntity.dateCreated} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="startDate">Start Date</span>
          </dt>
          <dd>
            <TextFormat value={registerRequestEntity.startDate} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="endDate">End Date</span>
          </dt>
          <dd>
            <TextFormat value={registerRequestEntity.endDate} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>Intern Register</dt>
          <dd>{registerRequestEntity.internRegisterCode ? registerRequestEntity.internRegisterCode : ''}</dd>
          <dt>Request Register</dt>
          <dd>{registerRequestEntity.requestRegisterPosition ? registerRequestEntity.requestRegisterPosition : ''}</dd>
        </dl>
        <Button tag={Link} to="/register-request" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/register-request/${registerRequestEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ registerRequest }: IRootState) => ({
  registerRequestEntity: registerRequest.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(RegisterRequestDetail);
