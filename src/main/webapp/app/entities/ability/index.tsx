import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Ability from './ability';
import AbilityDetail from './ability-detail';
import AbilityUpdate from './ability-update';
import AbilityDeleteDialog from './ability-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={AbilityDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AbilityUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AbilityUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AbilityDetail} />
      <ErrorBoundaryRoute path={match.url} component={Ability} />
    </Switch>
  </>
);

export default Routes;
