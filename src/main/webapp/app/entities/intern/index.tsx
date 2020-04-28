import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Intern from './intern';
import InternDetail from './intern-detail';
import InternUpdate from './intern-update';
import InternDeleteDialog from './intern-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={InternDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={InternUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={InternUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={InternDetail} />
      <ErrorBoundaryRoute path={match.url} component={Intern} />
    </Switch>
  </>
);

export default Routes;
