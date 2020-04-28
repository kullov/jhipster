import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Organization from './organization';
import Intern from './intern';
import Teacher from './teacher';
import Ability from './ability';
import RegisterRequest from './register-request';
import RequestAssignment from './request-assignment';
import Request from './request';
import AbilityCategory from './ability-category';
import Status from './status';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}organization`} component={Organization} />
      <ErrorBoundaryRoute path={`${match.url}intern`} component={Intern} />
      <ErrorBoundaryRoute path={`${match.url}teacher`} component={Teacher} />
      <ErrorBoundaryRoute path={`${match.url}ability`} component={Ability} />
      <ErrorBoundaryRoute path={`${match.url}register-request`} component={RegisterRequest} />
      <ErrorBoundaryRoute path={`${match.url}request-assignment`} component={RequestAssignment} />
      <ErrorBoundaryRoute path={`${match.url}request`} component={Request} />
      <ErrorBoundaryRoute path={`${match.url}ability-category`} component={AbilityCategory} />
      <ErrorBoundaryRoute path={`${match.url}status`} component={Status} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
