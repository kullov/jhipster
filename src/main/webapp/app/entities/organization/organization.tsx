import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './organization.reducer';
import { IOrganization } from 'app/shared/model/organization.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IOrganizationProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Organization = (props: IOrganizationProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { organizationList, match, loading } = props;
  return (
    <div>
      <h2 id="organization-heading">
        Organizations
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Organization
        </Link>
      </h2>
      <div className="table-responsive">
        {organizationList && organizationList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Employee Count</th>
                <th>Gross Revenue</th>
                <th>Name</th>
                <th>Tax Number</th>
                <th>Password</th>
                <th>Email</th>
                <th>Contact</th>
                <th>Description</th>
                <th>Address</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {organizationList.map((organization, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${organization.id}`} color="link" size="sm">
                      {organization.id}
                    </Button>
                  </td>
                  <td>{organization.employeeCount}</td>
                  <td>{organization.grossRevenue}</td>
                  <td>{organization.name}</td>
                  <td>{organization.taxNumber}</td>
                  <td>{organization.password}</td>
                  <td>{organization.email}</td>
                  <td>{organization.contact}</td>
                  <td>{organization.description}</td>
                  <td>{organization.address}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${organization.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${organization.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${organization.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Organizations found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ organization }: IRootState) => ({
  organizationList: organization.entities,
  loading: organization.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Organization);
