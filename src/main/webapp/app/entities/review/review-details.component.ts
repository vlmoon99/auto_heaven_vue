import { Component, Vue, Inject } from 'vue-property-decorator';

import { IReview } from '@/shared/model/review.model';
import ReviewService from './review.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class ReviewDetails extends Vue {
  @Inject('reviewService') private reviewService: () => ReviewService;
  @Inject('alertService') private alertService: () => AlertService;

  public review: IReview = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.reviewId) {
        vm.retrieveReview(to.params.reviewId);
      }
    });
  }

  public retrieveReview(reviewId) {
    this.reviewService()
      .find(reviewId)
      .then(res => {
        this.review = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
