import { IReview } from '@/shared/model/review.model';
import { IOrder } from '@/shared/model/order.model';

export interface IProduct {
  id?: number;
  countryOfOrigin?: string;
  available?: boolean;
  pictureUrls?: string;
  name?: string;
  vendor?: string | null;
  vendorCode?: string | null;
  propertiesContentType?: string;
  properties?: string;
  price?: number;
  totalRating?: number;
  description?: string | null;
  reviews?: IReview[] | null;
  order?: IOrder | null;
}

export class Product implements IProduct {
  constructor(
    public id?: number,
    public countryOfOrigin?: string,
    public available?: boolean,
    public pictureUrls?: string,
    public name?: string,
    public vendor?: string | null,
    public vendorCode?: string | null,
    public propertiesContentType?: string,
    public properties?: string,
    public price?: number,
    public totalRating?: number,
    public description?: string | null,
    public reviews?: IReview[] | null,
    public order?: IOrder | null
  ) {
    this.available = this.available ?? false;
  }
}
