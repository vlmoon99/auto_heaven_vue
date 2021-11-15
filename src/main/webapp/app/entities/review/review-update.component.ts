import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, decimal } from 'vuelidate/lib/validators';
import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import ProductService from '@/entities/product/product.service';
import { IProduct } from '@/shared/model/product.model';

import { IReview, Review } from '@/shared/model/review.model';
import ReviewService from './review.service';

const validations: any = {
  review: {
    userId: {
      required,
      numeric,
    },
    body: {
      required,
      minLength: minLength(5),
      maxLength: maxLength(256),
    },
    dateAdded: {
      required,
    },
    rating: {
      required,
      decimal,
    },
  },
};

@Component({
  validations,
})
export default class ReviewUpdate extends Vue {
  @Inject('reviewService') private reviewService: () => ReviewService;
  @Inject('alertService') private alertService: () => AlertService;

  public review: IReview = new Review();

  @Inject('productService') private productService: () => ProductService;

  public products: IProduct[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.reviewId) {
        vm.retrieveReview(to.params.reviewId);
      }
      vm.initRelationships();
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.review.id) {
      this.reviewService()
        .update(this.review)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('autoheavenApp.review.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    } else {
      this.reviewService()
        .create(this.review)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('autoheavenApp.review.created', { param: param.id });
          this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    }
  }

  public convertDateTimeFromServer(date: Date): string {
    if (date && dayjs(date).isValid()) {
      return dayjs(date).format(DATE_TIME_LONG_FORMAT);
    }
    return null;
  }

  public updateInstantField(field, event) {
    if (event.target.value) {
      this.review[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.review[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.review[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.review[field] = null;
    }
  }

  public retrieveReview(reviewId): void {
    this.reviewService()
      .find(reviewId)
      .then(res => {
        res.dateAdded = new Date(res.dateAdded);
        this.review = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.productService()
      .retrieve()
      .then(res => {
        this.products = res.data;
      });
  }
}
