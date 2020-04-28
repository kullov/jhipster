import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './intern.reducer';
import { IIntern } from 'app/shared/model/intern.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IInternProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Intern = (props: IInternProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { internList, match, loading } = props;
  return (
    <div>
      <h2 id="intern-heading">
        Interns
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Intern
        </Link>
      </h2>
      <div className="table-responsive">
        {internList && internList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Code</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Date Of Birth</th>
                <th>Join Date</th>
                <th>Class Name</th>
                <th>Avatar</th>
                <th>Password</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Description</th>
                <th>Address</th>
                <th>Intern Ability</th>
                <th>Organization Intern</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {internList.map((intern, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${intern.id}`} color="link" size="sm">
                      {intern.id}
                    </Button>
                  </td>
                  <td>{intern.code}</td>
                  <td>{intern.firstName}</td>
                  <td>{intern.lastName}</td>
                  <td>
                    <TextFormat type="date" value={intern.dateOfBirth} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={intern.joinDate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{intern.className}</td>
                  <td>{intern.avatar}</td>
                  <td>{intern.password}</td>
                  <td>{intern.email}</td>
                  <td>{intern.phone}</td>
                  <td>{intern.description}</td>
                  <td>{intern.address}</td>
                  <td>
                    {intern.internAbilities
                      ? intern.internAbilities.map((val, j) => (
                          <span key={j}>
                            <Link to={`ability/${val.id}`}>{val.name}</Link>
                            {j === intern.internAbilities.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td>
                    {intern.organizationInternId ? (
                      <Link to={`organization/${intern.organizationInternId}`}>{intern.organizationInternId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${intern.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${intern.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${intern.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Interns found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ intern }: IRootState) => ({
  internList: intern.entities,
  loading: intern.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Intern);
