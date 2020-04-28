import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IAbilityCategory } from 'app/shared/model/ability-category.model';
import { getEntities as getAbilityCategories } from 'app/entities/ability-category/ability-category.reducer';
import { IIntern } from 'app/shared/model/intern.model';
import { getEntities as getInterns } from 'app/entities/intern/intern.reducer';
import { IRequest } from 'app/shared/model/request.model';
import { getEntities as getRequests } from 'app/entities/request/request.reducer';
import { getEntity, updateEntity, createEntity, reset } from './ability.reducer';
import { IAbility } from 'app/shared/model/ability.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAbilityUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AbilityUpdate = (props: IAbilityUpdateProps) => {
  const [typeId, setTypeId] = useState('0');
  const [internId, setInternId] = useState('0');
  const [requestId, setRequestId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { abilityEntity, abilityCategories, interns, requests, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/ability');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getAbilityCategories();
    props.getInterns();
    props.getRequests();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...abilityEntity,
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
          <h2 id="aApp.ability.home.createOrEditLabel">Create or edit a Ability</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : abilityEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="ability-id">ID</Label>
                  <AvInput id="ability-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="ability-name">
                  Name
                </Label>
                <AvField id="ability-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="ability-description">
                  Description
                </Label>
                <AvField id="ability-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label for="ability-type">Type</Label>
                <AvInput id="ability-type" type="select" className="form-control" name="typeId">
                  <option value="" key="0" />
                  {abilityCategories
                    ? abilityCategories.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.name}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/ability" replace color="info">
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
  abilityCategories: storeState.abilityCategory.entities,
  interns: storeState.intern.entities,
  requests: storeState.request.entities,
  abilityEntity: storeState.ability.entity,
  loading: storeState.ability.loading,
  updating: storeState.ability.updating,
  updateSuccess: storeState.ability.updateSuccess
});

const mapDispatchToProps = {
  getAbilityCategories,
  getInterns,
  getRequests,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AbilityUpdate);
