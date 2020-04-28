import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IIntern, defaultValue } from 'app/shared/model/intern.model';

export const ACTION_TYPES = {
  FETCH_INTERN_LIST: 'intern/FETCH_INTERN_LIST',
  FETCH_INTERN: 'intern/FETCH_INTERN',
  CREATE_INTERN: 'intern/CREATE_INTERN',
  UPDATE_INTERN: 'intern/UPDATE_INTERN',
  DELETE_INTERN: 'intern/DELETE_INTERN',
  RESET: 'intern/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IIntern>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type InternState = Readonly<typeof initialState>;

// Reducer

export default (state: InternState = initialState, action): InternState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_INTERN_LIST):
    case REQUEST(ACTION_TYPES.FETCH_INTERN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_INTERN):
    case REQUEST(ACTION_TYPES.UPDATE_INTERN):
    case REQUEST(ACTION_TYPES.DELETE_INTERN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_INTERN_LIST):
    case FAILURE(ACTION_TYPES.FETCH_INTERN):
    case FAILURE(ACTION_TYPES.CREATE_INTERN):
    case FAILURE(ACTION_TYPES.UPDATE_INTERN):
    case FAILURE(ACTION_TYPES.DELETE_INTERN):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_INTERN_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_INTERN):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_INTERN):
    case SUCCESS(ACTION_TYPES.UPDATE_INTERN):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_INTERN):
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

const apiUrl = 'api/interns';

// Actions

export const getEntities: ICrudGetAllAction<IIntern> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_INTERN_LIST,
  payload: axios.get<IIntern>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IIntern> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_INTERN,
    payload: axios.get<IIntern>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IIntern> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_INTERN,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IIntern> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_INTERN,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IIntern> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_INTERN,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
