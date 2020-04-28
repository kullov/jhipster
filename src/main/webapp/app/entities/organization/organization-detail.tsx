import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './organization.reducer';
import { IOrganization } from 'app/shared/model/organization.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IOrganizationDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const OrganizationDetail = (props: IOrganizationDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { organizationEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Organization [<b>{organizationEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="employeeCount">Employee Count</span>
          </dt>
          <dd>{organizationEntity.employeeCount}</dd>
          <dt>
            <span id="grossRevenue">Gross Revenue</span>
          </dt>
          <dd>{organizationEntity.grossRevenue}</dd>
          <dt>
            <span id="name">Name</span>
          </dt>
          <dd>{organizationEntity.name}</dd>
          <dt>
            <span id="taxNumber">Tax Number</span>
          </dt>
          <dd>{organizationEntity.taxNumber}</dd>
          <dt>
            <span id="password">Password</span>
          </dt>
          <dd>{organizationEntity.password}</dd>
          <dt>
            <span id="email">Email</span>
          </dt>
          <dd>{organizationEntity.email}</dd>
          <dt>
            <span id="contact">Contact</span>
          </dt>
          <dd>{organizationEntity.contact}</dd>
          <dt>
            <span id="description">Description</span>
          </dt>
          <dd>{organizationEntity.description}</dd>
          <dt>
            <span id="address">Address</span>
          </dt>
          <dd>{organizationEntity.address}</dd>
        </dl>
        <Button tag={Link} to="/organization" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/organization/${organizationEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ organization }: IRootState) => ({
  organizationEntity: organization.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(OrganizationDetail);
