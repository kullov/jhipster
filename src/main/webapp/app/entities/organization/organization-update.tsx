import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './organization.reducer';
import { IOrganization } from 'app/shared/model/organization.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IOrganizationUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const OrganizationUpdate = (props: IOrganizationUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { organizationEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/organization');
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
        ...organizationEntity,
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
          <h2 id="aApp.organization.home.createOrEditLabel">Create or edit a Organization</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : organizationEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="organization-id">ID</Label>
                  <AvInput id="organization-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="employeeCountLabel" for="organization-employeeCount">
                  Employee Count
                </Label>
                <AvField id="organization-employeeCount" type="string" className="form-control" name="employeeCount" />
              </AvGroup>
              <AvGroup>
                <Label id="grossRevenueLabel" for="organization-grossRevenue">
                  Gross Revenue
                </Label>
                <AvField id="organization-grossRevenue" type="text" name="grossRevenue" />
              </AvGroup>
              <AvGroup>
                <Label id="nameLabel" for="organization-name">
                  Name
                </Label>
                <AvField id="organization-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="taxNumberLabel" for="organization-taxNumber">
                  Tax Number
                </Label>
                <AvField id="organization-taxNumber" type="text" name="taxNumber" />
              </AvGroup>
              <AvGroup>
                <Label id="passwordLabel" for="organization-password">
                  Password
                </Label>
                <AvField id="organization-password" type="text" name="password" />
              </AvGroup>
              <AvGroup>
                <Label id="emailLabel" for="organization-email">
                  Email
                </Label>
                <AvField id="organization-email" type="text" name="email" />
              </AvGroup>
              <AvGroup>
                <Label id="contactLabel" for="organization-contact">
                  Contact
                </Label>
                <AvField id="organization-contact" type="text" name="contact" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="organization-description">
                  Description
                </Label>
                <AvField id="organization-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="addressLabel" for="organization-address">
                  Address
                </Label>
                <AvField id="organization-address" type="text" name="address" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/organization" replace color="info">
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
  organizationEntity: storeState.organization.entity,
  loading: storeState.organization.loading,
  updating: storeState.organization.updating,
  updateSuccess: storeState.organization.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(OrganizationUpdate);
