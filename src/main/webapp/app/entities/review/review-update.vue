<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="autoheavenApp.review.home.createOrEditLabel"
          data-cy="ReviewCreateUpdateHeading"
          v-text="$t('autoheavenApp.review.home.createOrEditLabel')"
        >
          Create or edit a Review
        </h2>
        <div>
          <div class="form-group" v-if="review.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="review.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('autoheavenApp.review.userId')" for="review-userId">User Id</label>
            <input
              type="number"
              class="form-control"
              name="userId"
              id="review-userId"
              data-cy="userId"
              :class="{ valid: !$v.review.userId.$invalid, invalid: $v.review.userId.$invalid }"
              v-model.number="$v.review.userId.$model"
              required
            />
            <div v-if="$v.review.userId.$anyDirty && $v.review.userId.$invalid">
              <small class="form-text text-danger" v-if="!$v.review.userId.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.review.userId.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('autoheavenApp.review.body')" for="review-body">Body</label>
            <input
              type="text"
              class="form-control"
              name="body"
              id="review-body"
              data-cy="body"
              :class="{ valid: !$v.review.body.$invalid, invalid: $v.review.body.$invalid }"
              v-model="$v.review.body.$model"
              required
            />
            <div v-if="$v.review.body.$anyDirty && $v.review.body.$invalid">
              <small class="form-text text-danger" v-if="!$v.review.body.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.review.body.minLength" v-text="$t('entity.validation.minlength', { min: 5 })">
                This field is required to be at least 5 characters.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.review.body.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 256 })"
              >
                This field cannot be longer than 256 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('autoheavenApp.review.dateAdded')" for="review-dateAdded">Date Added</label>
            <div class="d-flex">
              <input
                id="review-dateAdded"
                data-cy="dateAdded"
                type="datetime-local"
                class="form-control"
                name="dateAdded"
                :class="{ valid: !$v.review.dateAdded.$invalid, invalid: $v.review.dateAdded.$invalid }"
                required
                :value="convertDateTimeFromServer($v.review.dateAdded.$model)"
                @change="updateInstantField('dateAdded', $event)"
              />
            </div>
            <div v-if="$v.review.dateAdded.$anyDirty && $v.review.dateAdded.$invalid">
              <small class="form-text text-danger" v-if="!$v.review.dateAdded.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.review.dateAdded.ZonedDateTimelocal"
                v-text="$t('entity.validation.ZonedDateTimelocal')"
              >
                This field should be a date and time.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('autoheavenApp.review.rating')" for="review-rating">Rating</label>
            <input
              type="number"
              class="form-control"
              name="rating"
              id="review-rating"
              data-cy="rating"
              :class="{ valid: !$v.review.rating.$invalid, invalid: $v.review.rating.$invalid }"
              v-model.number="$v.review.rating.$model"
              required
            />
            <div v-if="$v.review.rating.$anyDirty && $v.review.rating.$invalid">
              <small class="form-text text-danger" v-if="!$v.review.rating.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.review.rating.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('autoheavenApp.review.product')" for="review-product">Product</label>
            <select class="form-control" id="review-product" data-cy="product" name="product" v-model="review.product">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="review.product && productOption.id === review.product.id ? review.product : productOption"
                v-for="productOption in products"
                :key="productOption.id"
              >
                {{ productOption.id }}
              </option>
            </select>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.review.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./review-update.component.ts"></script>
