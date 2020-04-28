import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IRegisterRequest, defaultValue } from 'app/shared/model/register-request.model';

export const ACTION_TYPES = {
  FETCH_REGISTERREQUEST_LIST: 'registerRequest/FETCH_REGISTERREQUEST_LIST',
  FETCH_REGISTERREQUEST: 'registerRequest/FETCH_REGISTERREQUEST',
  CREATE_REGISTERREQUEST: 'registerRequest/CREATE_REGISTERREQUEST',
  UPDATE_REGISTERREQUEST: 'registerRequest/UPDATE_REGISTERREQUEST',
  DELETE_REGISTERREQUEST: 'registerRequest/DELETE_REGISTERREQUEST',
  RESET: 'registerRequest/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IRegisterRequest>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type RegisterRequestState = Readonly<typeof initialState>;

// Reducer

export default (state: RegisterRequestState = initialState, action): RegisterRequestState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_REGISTERREQUEST_LIST):
    case REQUEST(ACTION_TYPES.FETCH_REGISTERREQUEST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_REGISTERREQUEST):
    case REQUEST(ACTION_TYPES.UPDATE_REGISTERREQUEST):
    case REQUEST(ACTION_TYPES.DELETE_REGISTERREQUEST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_REGISTERREQUEST_LIST):
    case FAILURE(ACTION_TYPES.FETCH_REGISTERREQUEST):
    case FAILURE(ACTION_TYPES.CREATE_REGISTERREQUEST):
    case FAILURE(ACTION_TYPES.UPDATE_REGISTERREQUEST):
    case FAILURE(ACTION_TYPES.DELETE_REGISTERREQUEST):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_REGISTERREQUEST_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_REGISTERREQUEST):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_REGISTERREQUEST):
    case SUCCESS(ACTION_TYPES.UPDATE_REGISTERREQUEST):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_REGISTERREQUEST):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/register-requests';

// Actions

export const getEntities: ICrudGetAllAction<IRegisterRequest> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_REGISTERREQUEST_LIST,
  payload: axios.get<IRegisterRequest>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IRegisterRequest> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_REGISTERREQUEST,
    payload: axios.get<IRegisterRequest>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IRegisterRequest> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_REGISTERREQUEST,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IRegisterRequest> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_REGISTERREQUEST,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IRegisterRequest> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_REGISTERREQUEST,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
