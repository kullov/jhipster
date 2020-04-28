import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
import sessions, { SessionsState } from 'app/modules/account/sessions/sessions.reducer';
// prettier-ignore
import organization, {
  OrganizationState
} from 'app/entities/organization/organization.reducer';
// prettier-ignore
import intern, {
  InternState
} from 'app/entities/intern/intern.reducer';
// prettier-ignore
import teacher, {
  TeacherState
} from 'app/entities/teacher/teacher.reducer';
// prettier-ignore
import ability, {
  AbilityState
} from 'app/entities/ability/ability.reducer';
// prettier-ignore
import registerRequest, {
  RegisterRequestState
} from 'app/entities/register-request/register-request.reducer';
// prettier-ignore
import requestAssignment, {
  RequestAssignmentState
} from 'app/entities/request-assignment/request-assignment.reducer';
// prettier-ignore
import request, {
  RequestState
} from 'app/entities/request/request.reducer';
// prettier-ignore
import abilityCategory, {
  AbilityCategoryState
} from 'app/entities/ability-category/ability-category.reducer';
// prettier-ignore
import status, {
  StatusState
} from 'app/entities/status/status.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly sessions: SessionsState;
  readonly organization: OrganizationState;
  readonly intern: InternState;
  readonly teacher: TeacherState;
  readonly ability: AbilityState;
  readonly registerRequest: RegisterRequestState;
  readonly requestAssignment: RequestAssignmentState;
  readonly request: RequestState;
  readonly abilityCategory: AbilityCategoryState;
  readonly status: StatusState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  sessions,
  organization,
  intern,
  teacher,
  ability,
  registerRequest,
  requestAssignment,
  request,
  abilityCategory,
  status,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar
});

export default rootReducer;
