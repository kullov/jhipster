import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './teacher.reducer';
import { ITeacher } from 'app/shared/model/teacher.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITeacherUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TeacherUpdate = (props: ITeacherUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { teacherEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/teacher');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...teacherEntity,
        ...values
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="aApp.teacher.home.createOrEditLabel">Create or edit a Teacher</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : teacherEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="teacher-id">ID</Label>
                  <AvInput id="teacher-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="teacher-name">
                  Name
                </Label>
                <AvField id="teacher-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="classNameLabel" for="teacher-className">
                  Class Name
                </Label>
                <AvField id="teacher-className" type="text" name="className" />
              </AvGroup>
              <AvGroup>
                <Label id="contactLabel" for="teacher-contact">
                  Contact
                </Label>
                <AvField id="teacher-contact" type="string" className="form-control" name="contact" />
              </AvGroup>
              <AvGroup>
                <Label id="passwordLabel" for="teacher-password">
                  Password
                </Label>
                <AvField id="teacher-password" type="text" name="password" />
              </AvGroup>
              <AvGroup>
                <Label id="emailLabel" for="teacher-email">
                  Email
                </Label>
                <AvField id="teacher-email" type="text" name="email" />
              </AvGroup>
              <AvGroup>
                <Label id="addressLabel" for="teacher-address">
                  Address
                </Label>
                <AvField id="teacher-address" type="text" name="address" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/teacher" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  teacherEntity: storeState.teacher.entity,
  loading: storeState.teacher.loading,
  updating: storeState.teacher.updating,
  updateSuccess: storeState.teacher.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TeacherUpdate);
