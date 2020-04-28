import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAbility, defaultValue } from 'app/shared/model/ability.model';

export const ACTION_TYPES = {
  FETCH_ABILITY_LIST: 'ability/FETCH_ABILITY_LIST',
  FETCH_ABILITY: 'ability/FETCH_ABILITY',
  CREATE_ABILITY: 'ability/CREATE_ABILITY',
  UPDATE_ABILITY: 'ability/UPDATE_ABILITY',
  DELETE_ABILITY: 'ability/DELETE_ABILITY',
  RESET: 'ability/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAbility>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type AbilityState = Readonly<typeof initialState>;

// Reducer

export default (state: AbilityState = initialState, action): AbilityState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ABILITY_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ABILITY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ABILITY):
    case REQUEST(ACTION_TYPES.UPDATE_ABILITY):
    case REQUEST(ACTION_TYPES.DELETE_ABILITY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ABILITY_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ABILITY):
    case FAILURE(ACTION_TYPES.CREATE_ABILITY):
    case FAILURE(ACTION_TYPES.UPDATE_ABILITY):
    case FAILURE(ACTION_TYPES.DELETE_ABILITY):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ABILITY_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ABILITY):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ABILITY):
    case SUCCESS(ACTION_TYPES.UPDATE_ABILITY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ABILITY):
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

const apiUrl = 'api/abilities';

// Actions

export const getEntities: ICrudGetAllAction<IAbility> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ABILITY_LIST,
  payload: axios.get<IAbility>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IAbility> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ABILITY,
    payload: axios.get<IAbility>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IAbility> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ABILITY,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAbility> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ABILITY,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAbility> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ABILITY,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
