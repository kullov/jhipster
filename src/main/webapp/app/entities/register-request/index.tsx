import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import RegisterRequest from './register-request';
import RegisterRequestDetail from './register-request-detail';
import RegisterRequestUpdate from './register-request-update';
import RegisterRequestDeleteDialog from './register-request-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={RegisterRequestDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={RegisterRequestUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={RegisterRequestUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={RegisterRequestDetail} />
      <ErrorBoundaryRoute path={match.url} component={RegisterRequest} />
    </Switch>
  </>
);

export default Routes;
