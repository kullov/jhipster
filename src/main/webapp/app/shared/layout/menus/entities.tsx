import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  <NavDropdown icon="th-list" name="Entities" id="entity-menu" style={{ maxHeight: '80vh', overflow: 'auto' }}>
    <MenuItem icon="asterisk" to="/organization">
      Organization
    </MenuItem>
    <MenuItem icon="asterisk" to="/intern">
      Intern
    </MenuItem>
    <MenuItem icon="asterisk" to="/teacher">
      Teacher
    </MenuItem>
    <MenuItem icon="asterisk" to="/ability">
      Ability
    </MenuItem>
    <MenuItem icon="asterisk" to="/register-request">
      Register Request
    </MenuItem>
    <MenuItem icon="asterisk" to="/request-assignment">
      Request Assignment
    </MenuItem>
    <MenuItem icon="asterisk" to="/request">
      Request
    </MenuItem>
    <MenuItem icon="asterisk" to="/ability-category">
      Ability Category
    </MenuItem>
    <MenuItem icon="asterisk" to="/status">
      Status
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
