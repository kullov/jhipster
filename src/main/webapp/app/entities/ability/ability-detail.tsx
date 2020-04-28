import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './ability.reducer';
import { IAbility } from 'app/shared/model/ability.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAbilityDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AbilityDetail = (props: IAbilityDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { abilityEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Ability [<b>{abilityEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">Name</span>
          </dt>
          <dd>{abilityEntity.name}</dd>
          <dt>
            <span id="description">Description</span>
          </dt>
          <dd>{abilityEntity.description}</dd>
          <dt>Type</dt>
          <dd>{abilityEntity.typeName ? abilityEntity.typeName : ''}</dd>
        </dl>
        <Button tag={Link} to="/ability" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/ability/${abilityEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ ability }: IRootState) => ({
  abilityEntity: ability.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AbilityDetail);
