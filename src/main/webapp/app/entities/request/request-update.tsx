import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IAbility } from 'app/shared/model/ability.model';
import { getEntities as getAbilities } from 'app/entities/ability/ability.reducer';
import { IOrganization } from 'app/shared/model/organization.model';
import { getEntities as getOrganizations } from 'app/entities/organization/organization.reducer';
import { IStatus } from 'app/shared/model/status.model';
import { getEntities as getStatuses } from 'app/entities/status/status.reducer';
import { getEntity, updateEntity, createEntity, reset } from './request.reducer';
import { IRequest } from 'app/shared/model/request.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IRequestUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const RequestUpdate = (props: IRequestUpdateProps) => {
  const [idsrequestAbility, setIdsrequestAbility] = useState([]);
  const [organizationRequestId, setOrganizationRequestId] = useState('0');
  const [requestStatusId, setRequestStatusId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { requestEntity, abilities, organizations, statuses, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/request' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getAbilities();
    props.getOrganizations();
    props.getStatuses();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.dateCreated = convertDateTimeToServer(values.dateCreated);

    if (errors.length === 0) {
      const entity = {
        ...requestEntity,
        ...values,
        requestAbilities: mapIdList(values.requestAbilities)
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
          <h2 id="aApp.request.home.createOrEditLabel">Create or edit a Request</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : requestEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="request-id">ID</Label>
                  <AvInput id="request-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="positionLabel" for="request-position">
                  Position
                </Label>
                <AvField id="request-position" type="text" name="position" />
              </AvGroup>
              <AvGroup>
                <Label id="amountLabel" for="request-amount">
                  Amount
                </Label>
                <AvField id="request-amount" type="string" className="form-control" name="amount" />
              </AvGroup>
              <AvGroup>
                <Label id="dateCreatedLabel" for="request-dateCreated">
                  Date Created
                </Label>
                <AvInput
                  id="request-dateCreated"
                  type="datetime-local"
                  className="form-control"
                  name="dateCreated"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.requestEntity.dateCreated)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="statusLabel" for="request-status">
                  Status
                </Label>
                <AvField id="request-status" type="string" className="form-control" name="status" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="request-description">
                  Description
                </Label>
                <AvField id="request-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="typeLabel" for="request-type">
                  Type
                </Label>
                <AvField id="request-type" type="text" name="type" />
              </AvGroup>
              <AvGroup>
                <Label for="request-requestAbility">Request Ability</Label>
                <AvInput
                  id="request-requestAbility"
                  type="select"
                  multiple
                  className="form-control"
                  name="requestAbilities"
                  value={requestEntity.requestAbilities && requestEntity.requestAbilities.map(e => e.id)}
                >
                  <option value="" key="0" />
                  {abilities
                    ? abilities.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.name}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="request-organizationRequest">Organization Request</Label>
                <AvInput id="request-organizationRequest" type="select" className="form-control" name="organizationRequestId">
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
                <Label for="request-requestStatus">Request Status</Label>
                <AvInput id="request-requestStatus" type="select" className="form-control" name="requestStatusId">
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
              <Button tag={Link} id="cancel-save" to="/request" replace color="info">
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
  abilities: storeState.ability.entities,
  organizations: storeState.organization.entities,
  statuses: storeState.status.entities,
  requestEntity: storeState.request.entity,
  loading: storeState.request.loading,
  updating: storeState.request.updating,
  updateSuccess: storeState.request.updateSuccess
});

const mapDispatchToProps = {
  getAbilities,
  getOrganizations,
  getStatuses,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(RequestUpdate);
