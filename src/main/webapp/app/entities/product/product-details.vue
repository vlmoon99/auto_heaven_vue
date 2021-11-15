<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="product">
        <h2 class="jh-entity-heading" data-cy="productDetailsHeading">
          <span v-text="$t('autoheavenApp.product.detail.title')">Product</span> {{ product.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('autoheavenApp.product.countryOfOrigin')">Country Of Origin</span>
          </dt>
          <dd>
            <span>{{ product.countryOfOrigin }}</span>
          </dd>
          <dt>
            <span v-text="$t('autoheavenApp.product.available')">Available</span>
          </dt>
          <dd>
            <span>{{ product.available }}</span>
          </dd>
          <dt>
            <span v-text="$t('autoheavenApp.product.pictureUrls')">Picture Urls</span>
          </dt>
          <dd>
            <span>{{ product.pictureUrls }}</span>
          </dd>
          <dt>
            <span v-text="$t('autoheavenApp.product.name')">Name</span>
          </dt>
          <dd>
            <span>{{ product.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('autoheavenApp.product.vendor')">Vendor</span>
          </dt>
          <dd>
            <span>{{ product.vendor }}</span>
          </dd>
          <dt>
            <span v-text="$t('autoheavenApp.product.vendorCode')">Vendor Code</span>
          </dt>
          <dd>
            <span>{{ product.vendorCode }}</span>
          </dd>
          <dt>
            <span v-text="$t('autoheavenApp.product.properties')">Properties</span>
          </dt>
          <dd>
            <div v-if="product.properties">
              <a v-on:click="openFile(product.propertiesContentType, product.properties)" v-text="$t('entity.action.open')">open</a>
              {{ product.propertiesContentType }}, {{ byteSize(product.properties) }}
            </div>
          </dd>
          <dt>
            <span v-text="$t('autoheavenApp.product.price')">Price</span>
          </dt>
          <dd>
            <span>{{ product.price }}</span>
          </dd>
          <dt>
            <span v-text="$t('autoheavenApp.product.totalRating')">Total Rating</span>
          </dt>
          <dd>
            <span>{{ product.totalRating }}</span>
          </dd>
          <dt>
            <span v-text="$t('autoheavenApp.product.description')">Description</span>
          </dt>
          <dd>
            <span>{{ product.description }}</span>
          </dd>
          <dt>
            <span v-text="$t('autoheavenApp.product.order')">Order</span>
          </dt>
          <dd>
            <div v-if="product.order">
              <router-link :to="{ name: 'OrderView', params: { orderId: product.order.id } }">{{ product.order.id }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="product.id" :to="{ name: 'ProductEdit', params: { productId: product.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./product-details.component.ts"></script>
