import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import RequestAssignment from './request-assignment';
import RequestAssignmentDetail from './request-assignment-detail';
import RequestAssignmentUpdate from './request-assignment-update';
import RequestAssignmentDeleteDialog from './request-assignment-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={RequestAssignmentDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={RequestAssignmentUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={RequestAssignmentUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={RequestAssignmentDetail} />
      <ErrorBoundaryRoute path={match.url} component={RequestAssignment} />
    </Switch>
  </>
);

export default Routes;
