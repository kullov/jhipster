import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './intern.reducer';
import { IIntern } from 'app/shared/model/intern.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IInternDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const InternDetail = (props: IInternDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { internEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Intern [<b>{internEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="code">Code</span>
          </dt>
          <dd>{internEntity.code}</dd>
          <dt>
            <span id="firstName">First Name</span>
          </dt>
          <dd>{internEntity.firstName}</dd>
          <dt>
            <span id="lastName">Last Name</span>
          </dt>
          <dd>{internEntity.lastName}</dd>
          <dt>
            <span id="dateOfBirth">Date Of Birth</span>
          </dt>
          <dd>
            <TextFormat value={internEntity.dateOfBirth} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="joinDate">Join Date</span>
          </dt>
          <dd>
            <TextFormat value={internEntity.joinDate} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="className">Class Name</span>
          </dt>
          <dd>{internEntity.className}</dd>
          <dt>
            <span id="avatar">Avatar</span>
          </dt>
          <dd>{internEntity.avatar}</dd>
          <dt>
            <span id="password">Password</span>
          </dt>
          <dd>{internEntity.password}</dd>
          <dt>
            <span id="email">Email</span>
          </dt>
          <dd>{internEntity.email}</dd>
          <dt>
            <span id="phone">Phone</span>
          </dt>
          <dd>{internEntity.phone}</dd>
          <dt>
            <span id="description">Description</span>
          </dt>
          <dd>{internEntity.description}</dd>
          <dt>
            <span id="address">Address</span>
          </dt>
          <dd>{internEntity.address}</dd>
          <dt>Intern Ability</dt>
          <dd>
            {internEntity.internAbilities
              ? internEntity.internAbilities.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.name}</a>
                    {i === internEntity.internAbilities.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>Organization Intern</dt>
          <dd>{internEntity.organizationInternId ? internEntity.organizationInternId : ''}</dd>
        </dl>
        <Button tag={Link} to="/intern" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/intern/${internEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ intern }: IRootState) => ({
  internEntity: intern.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(InternDetail);
