<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="review">
        <h2 class="jh-entity-heading" data-cy="reviewDetailsHeading">
          <span v-text="$t('autoheavenApp.review.detail.title')">Review</span> {{ review.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('autoheavenApp.review.userId')">User Id</span>
          </dt>
          <dd>
            <span>{{ review.userId }}</span>
          </dd>
          <dt>
            <span v-text="$t('autoheavenApp.review.body')">Body</span>
          </dt>
          <dd>
            <span>{{ review.body }}</span>
          </dd>
          <dt>
            <span v-text="$t('autoheavenApp.review.dateAdded')">Date Added</span>
          </dt>
          <dd>
            <span v-if="review.dateAdded">{{ $d(Date.parse(review.dateAdded), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('autoheavenApp.review.rating')">Rating</span>
          </dt>
          <dd>
            <span>{{ review.rating }}</span>
          </dd>
          <dt>
            <span v-text="$t('autoheavenApp.review.product')">Product</span>
          </dt>
          <dd>
            <div v-if="review.product">
              <router-link :to="{ name: 'ProductView', params: { productId: review.product.id } }">{{ review.product.id }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="review.id" :to="{ name: 'ReviewEdit', params: { reviewId: review.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./review-details.component.ts"></script>
