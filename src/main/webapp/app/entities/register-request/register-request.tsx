import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './register-request.reducer';
import { IRegisterRequest } from 'app/shared/model/register-request.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IRegisterRequestProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const RegisterRequest = (props: IRegisterRequestProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { registerRequestList, match, loading } = props;
  return (
    <div>
      <h2 id="register-request-heading">
        Register Requests
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Register Request
        </Link>
      </h2>
      <div className="table-responsive">
        {registerRequestList && registerRequestList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Date Created</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Intern Register</th>
                <th>Request Register</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {registerRequestList.map((registerRequest, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${registerRequest.id}`} color="link" size="sm">
                      {registerRequest.id}
                    </Button>
                  </td>
                  <td>
                    <TextFormat type="date" value={registerRequest.dateCreated} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={registerRequest.startDate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={registerRequest.endDate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    {registerRequest.internRegisterCode ? (
                      <Link to={`intern/${registerRequest.internRegisterId}`}>{registerRequest.internRegisterCode}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {registerRequest.requestRegisterPosition ? (
                      <Link to={`request/${registerRequest.requestRegisterId}`}>{registerRequest.requestRegisterPosition}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${registerRequest.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${registerRequest.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${registerRequest.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Register Requests found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ registerRequest }: IRootState) => ({
  registerRequestList: registerRequest.entities,
  loading: registerRequest.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(RegisterRequest);
