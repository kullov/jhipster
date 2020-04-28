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
import { getEntity, updateEntity, createEntity, reset } from './intern.reducer';
import { IIntern } from 'app/shared/model/intern.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IInternUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const InternUpdate = (props: IInternUpdateProps) => {
  const [idsinternAbility, setIdsinternAbility] = useState([]);
  const [organizationInternId, setOrganizationInternId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { internEntity, abilities, organizations, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/intern');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getAbilities();
    props.getOrganizations();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.dateOfBirth = convertDateTimeToServer(values.dateOfBirth);
    values.joinDate = convertDateTimeToServer(values.joinDate);

    if (errors.length === 0) {
      const entity = {
        ...internEntity,
        ...values,
        internAbilities: mapIdList(values.internAbilities)
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
          <h2 id="aApp.intern.home.createOrEditLabel">Create or edit a Intern</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : internEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="intern-id">ID</Label>
                  <AvInput id="intern-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="codeLabel" for="intern-code">
                  Code
                </Label>
                <AvField id="intern-code" type="string" className="form-control" name="code" />
              </AvGroup>
              <AvGroup>
                <Label id="firstNameLabel" for="intern-firstName">
                  First Name
                </Label>
                <AvField id="intern-firstName" type="text" name="firstName" />
              </AvGroup>
              <AvGroup>
                <Label id="lastNameLabel" for="intern-lastName">
                  Last Name
                </Label>
                <AvField id="intern-lastName" type="text" name="lastName" />
              </AvGroup>
              <AvGroup>
                <Label id="dateOfBirthLabel" for="intern-dateOfBirth">
                  Date Of Birth
                </Label>
                <AvInput
                  id="intern-dateOfBirth"
                  type="datetime-local"
                  className="form-control"
                  name="dateOfBirth"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.internEntity.dateOfBirth)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="joinDateLabel" for="intern-joinDate">
                  Join Date
                </Label>
                <AvInput
                  id="intern-joinDate"
                  type="datetime-local"
                  className="form-control"
                  name="joinDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.internEntity.joinDate)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="classNameLabel" for="intern-className">
                  Class Name
                </Label>
                <AvField id="intern-className" type="text" name="className" />
              </AvGroup>
              <AvGroup>
                <Label id="avatarLabel" for="intern-avatar">
                  Avatar
                </Label>
                <AvField id="intern-avatar" type="text" name="avatar" />
              </AvGroup>
              <AvGroup>
                <Label id="passwordLabel" for="intern-password">
                  Password
                </Label>
                <AvField id="intern-password" type="text" name="password" />
              </AvGroup>
              <AvGroup>
                <Label id="emailLabel" for="intern-email">
                  Email
                </Label>
                <AvField id="intern-email" type="text" name="email" />
              </AvGroup>
              <AvGroup>
                <Label id="phoneLabel" for="intern-phone">
                  Phone
                </Label>
                <AvField id="intern-phone" type="string" className="form-control" name="phone" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="intern-description">
                  Description
                </Label>
                <AvField id="intern-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="addressLabel" for="intern-address">
                  Address
                </Label>
                <AvField id="intern-address" type="text" name="address" />
              </AvGroup>
              <AvGroup>
                <Label for="intern-internAbility">Intern Ability</Label>
                <AvInput
                  id="intern-internAbility"
                  type="select"
                  multiple
                  className="form-control"
                  name="internAbilities"
                  value={internEntity.internAbilities && internEntity.internAbilities.map(e => e.id)}
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
                <Label for="intern-organizationIntern">Organization Intern</Label>
                <AvInput id="intern-organizationIntern" type="select" className="form-control" name="organizationInternId">
                  <option value="" key="0" />
                  {organizations
                    ? organizations.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/intern" replace color="info">
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
  internEntity: storeState.intern.entity,
  loading: storeState.intern.loading,
  updating: storeState.intern.updating,
  updateSuccess: storeState.intern.updateSuccess
});

const mapDispatchToProps = {
  getAbilities,
  getOrganizations,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(InternUpdate);
