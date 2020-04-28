import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IRequestAssignment, defaultValue } from 'app/shared/model/request-assignment.model';

export const ACTION_TYPES = {
  FETCH_REQUESTASSIGNMENT_LIST: 'requestAssignment/FETCH_REQUESTASSIGNMENT_LIST',
  FETCH_REQUESTASSIGNMENT: 'requestAssignment/FETCH_REQUESTASSIGNMENT',
  CREATE_REQUESTASSIGNMENT: 'requestAssignment/CREATE_REQUESTASSIGNMENT',
  UPDATE_REQUESTASSIGNMENT: 'requestAssignment/UPDATE_REQUESTASSIGNMENT',
  DELETE_REQUESTASSIGNMENT: 'requestAssignment/DELETE_REQUESTASSIGNMENT',
  RESET: 'requestAssignment/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IRequestAssignment>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type RequestAssignmentState = Readonly<typeof initialState>;

// Reducer

export default (state: RequestAssignmentState = initialState, action): RequestAssignmentState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_REQUESTASSIGNMENT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_REQUESTASSIGNMENT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_REQUESTASSIGNMENT):
    case REQUEST(ACTION_TYPES.UPDATE_REQUESTASSIGNMENT):
    case REQUEST(ACTION_TYPES.DELETE_REQUESTASSIGNMENT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_REQUESTASSIGNMENT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_REQUESTASSIGNMENT):
    case FAILURE(ACTION_TYPES.CREATE_REQUESTASSIGNMENT):
    case FAILURE(ACTION_TYPES.UPDATE_REQUESTASSIGNMENT):
    case FAILURE(ACTION_TYPES.DELETE_REQUESTASSIGNMENT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_REQUESTASSIGNMENT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_REQUESTASSIGNMENT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_REQUESTASSIGNMENT):
    case SUCCESS(ACTION_TYPES.UPDATE_REQUESTASSIGNMENT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_REQUESTASSIGNMENT):
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

const apiUrl = 'api/request-assignments';

// Actions

export const getEntities: ICrudGetAllAction<IRequestAssignment> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_REQUESTASSIGNMENT_LIST,
  payload: axios.get<IRequestAssignment>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IRequestAssignment> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_REQUESTASSIGNMENT,
    payload: axios.get<IRequestAssignment>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IRequestAssignment> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_REQUESTASSIGNMENT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IRequestAssignment> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_REQUESTASSIGNMENT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IRequestAssignment> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_REQUESTASSIGNMENT,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
