import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAbilityCategory, defaultValue } from 'app/shared/model/ability-category.model';

export const ACTION_TYPES = {
  FETCH_ABILITYCATEGORY_LIST: 'abilityCategory/FETCH_ABILITYCATEGORY_LIST',
  FETCH_ABILITYCATEGORY: 'abilityCategory/FETCH_ABILITYCATEGORY',
  CREATE_ABILITYCATEGORY: 'abilityCategory/CREATE_ABILITYCATEGORY',
  UPDATE_ABILITYCATEGORY: 'abilityCategory/UPDATE_ABILITYCATEGORY',
  DELETE_ABILITYCATEGORY: 'abilityCategory/DELETE_ABILITYCATEGORY',
  RESET: 'abilityCategory/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAbilityCategory>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type AbilityCategoryState = Readonly<typeof initialState>;

// Reducer

export default (state: AbilityCategoryState = initialState, action): AbilityCategoryState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ABILITYCATEGORY_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ABILITYCATEGORY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ABILITYCATEGORY):
    case REQUEST(ACTION_TYPES.UPDATE_ABILITYCATEGORY):
    case REQUEST(ACTION_TYPES.DELETE_ABILITYCATEGORY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ABILITYCATEGORY_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ABILITYCATEGORY):
    case FAILURE(ACTION_TYPES.CREATE_ABILITYCATEGORY):
    case FAILURE(ACTION_TYPES.UPDATE_ABILITYCATEGORY):
    case FAILURE(ACTION_TYPES.DELETE_ABILITYCATEGORY):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ABILITYCATEGORY_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ABILITYCATEGORY):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ABILITYCATEGORY):
    case SUCCESS(ACTION_TYPES.UPDATE_ABILITYCATEGORY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ABILITYCATEGORY):
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

const apiUrl = 'api/ability-categories';

// Actions

export const getEntities: ICrudGetAllAction<IAbilityCategory> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ABILITYCATEGORY_LIST,
  payload: axios.get<IAbilityCategory>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IAbilityCategory> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ABILITYCATEGORY,
    payload: axios.get<IAbilityCategory>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IAbilityCategory> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ABILITYCATEGORY,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAbilityCategory> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ABILITYCATEGORY,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAbilityCategory> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ABILITYCATEGORY,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
