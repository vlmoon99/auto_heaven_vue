export interface ICategory {
  id?: number;
  xmlCategoryId?: number | null;
  parentId?: number | null;
  name?: string;
}

export class Category implements ICategory {
  constructor(public id?: number, public xmlCategoryId?: number | null, public parentId?: number | null, public name?: string) {}
}
