export interface ITeacher {
  id?: number;
  name?: string;
  className?: string;
  contact?: number;
  password?: string;
  email?: string;
  address?: string;
}

export const defaultValue: Readonly<ITeacher> = {};
