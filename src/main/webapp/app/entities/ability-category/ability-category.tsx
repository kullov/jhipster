import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './ability-category.reducer';
import { IAbilityCategory } from 'app/shared/model/ability-category.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAbilityCategoryProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const AbilityCategory = (props: IAbilityCategoryProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { abilityCategoryList, match, loading } = props;
  return (
    <div>
      <h2 id="ability-category-heading">
        Ability Categories
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Ability Category
        </Link>
      </h2>
      <div className="table-responsive">
        {abilityCategoryList && abilityCategoryList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Name</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {abilityCategoryList.map((abilityCategory, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${abilityCategory.id}`} color="link" size="sm">
                      {abilityCategory.id}
                    </Button>
                  </td>
                  <td>{abilityCategory.name}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${abilityCategory.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${abilityCategory.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${abilityCategory.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Ability Categories found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ abilityCategory }: IRootState) => ({
  abilityCategoryList: abilityCategory.entities,
  loading: abilityCategory.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AbilityCategory);
