import { IProduct } from '@/shared/model/product.model';

export interface IOrder {
  id?: number;
  name?: string | null;
  userId?: string | null;
  dateAdded?: Date | null;
  products?: IProduct[] | null;
}

export class Order implements IOrder {
  constructor(
    public id?: number,
    public name?: string | null,
    public userId?: string | null,
    public dateAdded?: Date | null,
    public products?: IProduct[] | null
  ) {}
}
