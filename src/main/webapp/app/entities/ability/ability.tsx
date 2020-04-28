import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './ability.reducer';
import { IAbility } from 'app/shared/model/ability.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAbilityProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Ability = (props: IAbilityProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { abilityList, match, loading } = props;
  return (
    <div>
      <h2 id="ability-heading">
        Abilities
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Ability
        </Link>
      </h2>
      <div className="table-responsive">
        {abilityList && abilityList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Type</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {abilityList.map((ability, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${ability.id}`} color="link" size="sm">
                      {ability.id}
                    </Button>
                  </td>
                  <td>{ability.name}</td>
                  <td>{ability.description}</td>
                  <td>{ability.typeName ? <Link to={`ability-category/${ability.typeId}`}>{ability.typeName}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${ability.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${ability.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${ability.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Abilities found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ ability }: IRootState) => ({
  abilityList: ability.entities,
  loading: ability.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Ability);
