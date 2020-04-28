import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IIntern } from 'app/shared/model/intern.model';
import { getEntities as getInterns } from 'app/entities/intern/intern.reducer';
import { IRequest } from 'app/shared/model/request.model';
import { getEntities as getRequests } from 'app/entities/request/request.reducer';
import { getEntity, updateEntity, createEntity, reset } from './register-request.reducer';
import { IRegisterRequest } from 'app/shared/model/register-request.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IRegisterRequestUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const RegisterRequestUpdate = (props: IRegisterRequestUpdateProps) => {
  const [internRegisterId, setInternRegisterId] = useState('0');
  const [requestRegisterId, setRequestRegisterId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { registerRequestEntity, interns, requests, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/register-request');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getInterns();
    props.getRequests();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.dateCreated = convertDateTimeToServer(values.dateCreated);
    values.startDate = convertDateTimeToServer(values.startDate);
    values.endDate = convertDateTimeToServer(values.endDate);

    if (errors.length === 0) {
      const entity = {
        ...registerRequestEntity,
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
          <h2 id="aApp.registerRequest.home.createOrEditLabel">Create or edit a RegisterRequest</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : registerRequestEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="register-request-id">ID</Label>
                  <AvInput id="register-request-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="dateCreatedLabel" for="register-request-dateCreated">
                  Date Created
                </Label>
                <AvInput
                  id="register-request-dateCreated"
                  type="datetime-local"
                  className="form-control"
                  name="dateCreated"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.registerRequestEntity.dateCreated)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="startDateLabel" for="register-request-startDate">
                  Start Date
                </Label>
                <AvInput
                  id="register-request-startDate"
                  type="datetime-local"
                  className="form-control"
                  name="startDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.registerRequestEntity.startDate)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="endDateLabel" for="register-request-endDate">
                  End Date
                </Label>
                <AvInput
                  id="register-request-endDate"
                  type="datetime-local"
                  className="form-control"
                  name="endDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.registerRequestEntity.endDate)}
                />
              </AvGroup>
              <AvGroup>
                <Label for="register-request-internRegister">Intern Register</Label>
                <AvInput id="register-request-internRegister" type="select" className="form-control" name="internRegisterId">
                  <option value="" key="0" />
                  {interns
                    ? interns.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.code}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="register-request-requestRegister">Request Register</Label>
                <AvInput id="register-request-requestRegister" type="select" className="form-control" name="requestRegisterId">
                  <option value="" key="0" />
                  {requests
                    ? requests.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.position}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/register-request" replace color="info">
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
  interns: storeState.intern.entities,
  requests: storeState.request.entities,
  registerRequestEntity: storeState.registerRequest.entity,
  loading: storeState.registerRequest.loading,
  updating: storeState.registerRequest.updating,
  updateSuccess: storeState.registerRequest.updateSuccess
});

const mapDispatchToProps = {
  getInterns,
  getRequests,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(RegisterRequestUpdate);
