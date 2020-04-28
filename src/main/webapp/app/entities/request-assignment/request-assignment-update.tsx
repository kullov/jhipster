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
import { IOrganization } from 'app/shared/model/organization.model';
import { getEntities as getOrganizations } from 'app/entities/organization/organization.reducer';
import { IStatus } from 'app/shared/model/status.model';
import { getEntities as getStatuses } from 'app/entities/status/status.reducer';
import { getEntity, updateEntity, createEntity, reset } from './request-assignment.reducer';
import { IRequestAssignment } from 'app/shared/model/request-assignment.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IRequestAssignmentUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const RequestAssignmentUpdate = (props: IRequestAssignmentUpdateProps) => {
  const [internRequestAssignmentId, setInternRequestAssignmentId] = useState('0');
  const [organizationRequestAssignmentId, setOrganizationRequestAssignmentId] = useState('0');
  const [statusId, setStatusId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { requestAssignmentEntity, interns, organizations, statuses, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/request-assignment');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getInterns();
    props.getOrganizations();
    props.getStatuses();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.startDate = convertDateTimeToServer(values.startDate);
    values.endDate = convertDateTimeToServer(values.endDate);
    values.dateCreated = convertDateTimeToServer(values.dateCreated);

    if (errors.length === 0) {
      const entity = {
        ...requestAssignmentEntity,
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
          <h2 id="aApp.requestAssignment.home.createOrEditLabel">Create or edit a RequestAssignment</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : requestAssignmentEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="request-assignment-id">ID</Label>
                  <AvInput id="request-assignment-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="startDateLabel" for="request-assignment-startDate">
                  Start Date
                </Label>
                <AvInput
                  id="request-assignment-startDate"
                  type="datetime-local"
                  className="form-control"
                  name="startDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.requestAssignmentEntity.startDate)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="endDateLabel" for="request-assignment-endDate">
                  End Date
                </Label>
                <AvInput
                  id="request-assignment-endDate"
                  type="datetime-local"
                  className="form-control"
                  name="endDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.requestAssignmentEntity.endDate)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="dateCreatedLabel" for="request-assignment-dateCreated">
                  Date Created
                </Label>
                <AvInput
                  id="request-assignment-dateCreated"
                  type="datetime-local"
                  className="form-control"
                  name="dateCreated"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.requestAssignmentEntity.dateCreated)}
                />
              </AvGroup>
              <AvGroup>
                <Label for="request-assignment-internRequestAssignment">Intern Request Assignment</Label>
                <AvInput
                  id="request-assignment-internRequestAssignment"
                  type="select"
                  className="form-control"
                  name="internRequestAssignmentId"
                >
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
                <Label for="request-assignment-organizationRequestAssignment">Organization Request Assignment</Label>
                <AvInput
                  id="request-assignment-organizationRequestAssignment"
                  type="select"
                  className="form-control"
                  name="organizationRequestAssignmentId"
                >
                  <option value="" key="0" />
                  {organizations
                    ? organizations.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.name}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="request-assignment-status">Status</Label>
                <AvInput id="request-assignment-status" type="select" className="form-control" name="statusId">
                  <option value="" key="0" />
                  {statuses
                    ? statuses.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.name}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/request-assignment" replace color="info">
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
  organizations: storeState.organization.entities,
  statuses: storeState.status.entities,
  requestAssignmentEntity: storeState.requestAssignment.entity,
  loading: storeState.requestAssignment.loading,
  updating: storeState.requestAssignment.updating,
  updateSuccess: storeState.requestAssignment.updateSuccess
});

const mapDispatchToProps = {
  getInterns,
  getOrganizations,
  getStatuses,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(RequestAssignmentUpdate);
