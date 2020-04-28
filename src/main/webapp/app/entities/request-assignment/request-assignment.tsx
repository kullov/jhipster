import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './request-assignment.reducer';
import { IRequestAssignment } from 'app/shared/model/request-assignment.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IRequestAssignmentProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const RequestAssignment = (props: IRequestAssignmentProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { requestAssignmentList, match, loading } = props;
  return (
    <div>
      <h2 id="request-assignment-heading">
        Request Assignments
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Request Assignment
        </Link>
      </h2>
      <div className="table-responsive">
        {requestAssignmentList && requestAssignmentList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Date Created</th>
                <th>Intern Request Assignment</th>
                <th>Organization Request Assignment</th>
                <th>Status</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {requestAssignmentList.map((requestAssignment, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${requestAssignment.id}`} color="link" size="sm">
                      {requestAssignment.id}
                    </Button>
                  </td>
                  <td>
                    <TextFormat type="date" value={requestAssignment.startDate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={requestAssignment.endDate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={requestAssignment.dateCreated} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    {requestAssignment.internRequestAssignmentCode ? (
                      <Link to={`intern/${requestAssignment.internRequestAssignmentId}`}>
                        {requestAssignment.internRequestAssignmentCode}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {requestAssignment.organizationRequestAssignmentName ? (
                      <Link to={`organization/${requestAssignment.organizationRequestAssignmentId}`}>
                        {requestAssignment.organizationRequestAssignmentName}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {requestAssignment.statusName ? (
                      <Link to={`status/${requestAssignment.statusId}`}>{requestAssignment.statusName}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${requestAssignment.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${requestAssignment.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${requestAssignment.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Request Assignments found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ requestAssignment }: IRootState) => ({
  requestAssignmentList: requestAssignment.entities,
  loading: requestAssignment.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(RequestAssignment);
