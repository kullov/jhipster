import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './teacher.reducer';
import { ITeacher } from 'app/shared/model/teacher.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITeacherDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TeacherDetail = (props: ITeacherDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { teacherEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Teacher [<b>{teacherEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">Name</span>
          </dt>
          <dd>{teacherEntity.name}</dd>
          <dt>
            <span id="className">Class Name</span>
          </dt>
          <dd>{teacherEntity.className}</dd>
          <dt>
            <span id="contact">Contact</span>
          </dt>
          <dd>{teacherEntity.contact}</dd>
          <dt>
            <span id="password">Password</span>
          </dt>
          <dd>{teacherEntity.password}</dd>
          <dt>
            <span id="email">Email</span>
          </dt>
          <dd>{teacherEntity.email}</dd>
          <dt>
            <span id="address">Address</span>
          </dt>
          <dd>{teacherEntity.address}</dd>
        </dl>
        <Button tag={Link} to="/teacher" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/teacher/${teacherEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ teacher }: IRootState) => ({
  teacherEntity: teacher.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TeacherDetail);
