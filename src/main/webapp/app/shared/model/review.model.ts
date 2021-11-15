import { IProduct } from '@/shared/model/product.model';

export interface IReview {
  id?: number;
  userId?: number;
  body?: string;
  dateAdded?: Date;
  rating?: number;
  product?: IProduct | null;
}

export class Review implements IReview {
  constructor(
    public id?: number,
    public userId?: number,
    public body?: string,
    public dateAdded?: Date,
    public rating?: number,
    public product?: IProduct | null
  ) {}
}
