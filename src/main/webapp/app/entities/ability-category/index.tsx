import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AbilityCategory from './ability-category';
import AbilityCategoryDetail from './ability-category-detail';
import AbilityCategoryUpdate from './ability-category-update';
import AbilityCategoryDeleteDialog from './ability-category-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={AbilityCategoryDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AbilityCategoryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AbilityCategoryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AbilityCategoryDetail} />
      <ErrorBoundaryRoute path={match.url} component={AbilityCategory} />
    </Switch>
  </>
);

export default Routes;
