<template>
  <div>
    <h2 id="page-heading" data-cy="ProductHeading">
      <span v-text="$t('autoheavenApp.product.home.title')" id="product-heading">Products</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('autoheavenApp.product.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'ProductCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-product"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('autoheavenApp.product.home.createLabel')"> Create a new Product </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && products && products.length === 0">
      <span v-text="$t('autoheavenApp.product.home.notFound')">No products found</span>
    </div>
    <div class="table-responsive" v-if="products && products.length > 0">
      <table class="table table-striped" aria-describedby="products">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="$t('global.field.id')">ID</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('countryOfOrigin')">
              <span v-text="$t('autoheavenApp.product.countryOfOrigin')">Country Of Origin</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'countryOfOrigin'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('available')">
              <span v-text="$t('autoheavenApp.product.available')">Available</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'available'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('pictureUrls')">
              <span v-text="$t('autoheavenApp.product.pictureUrls')">Picture Urls</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'pictureUrls'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('name')">
              <span v-text="$t('autoheavenApp.product.name')">Name</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('vendor')">
              <span v-text="$t('autoheavenApp.product.vendor')">Vendor</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'vendor'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('vendorCode')">
              <span v-text="$t('autoheavenApp.product.vendorCode')">Vendor Code</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'vendorCode'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('properties')">
              <span v-text="$t('autoheavenApp.product.properties')">Properties</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'properties'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('price')">
              <span v-text="$t('autoheavenApp.product.price')">Price</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'price'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('totalRating')">
              <span v-text="$t('autoheavenApp.product.totalRating')">Total Rating</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'totalRating'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('description')">
              <span v-text="$t('autoheavenApp.product.description')">Description</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'description'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('order.id')">
              <span v-text="$t('autoheavenApp.product.order')">Order</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'order.id'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="product in products" :key="product.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ProductView', params: { productId: product.id } }">{{ product.id }}</router-link>
            </td>
            <td>{{ product.countryOfOrigin }}</td>
            <td>{{ product.available }}</td>
            <td>{{ product.pictureUrls }}</td>
            <td>{{ product.name }}</td>
            <td>{{ product.vendor }}</td>
            <td>{{ product.vendorCode }}</td>
            <td>
              <a
                v-if="product.properties"
                v-on:click="openFile(product.propertiesContentType, product.properties)"
                v-text="$t('entity.action.open')"
                >open</a
              >
              <span v-if="product.properties">{{ product.propertiesContentType }}, {{ byteSize(product.properties) }}</span>
            </td>
            <td>{{ product.price }}</td>
            <td>{{ product.totalRating }}</td>
            <td>{{ product.description }}</td>
            <td>
              <div v-if="product.order">
                <router-link :to="{ name: 'OrderView', params: { orderId: product.order.id } }">{{ product.order.id }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ProductView', params: { productId: product.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ProductEdit', params: { productId: product.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(product)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
        <infinite-loading
          ref="infiniteLoading"
          v-if="totalItems > itemsPerPage"
          :identifier="infiniteId"
          slot="append"
          @infinite="loadMore"
          force-use-infinite-wrapper=".el-table__body-wrapper"
          :distance="20"
        >
        </infinite-loading>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="autoheavenApp.product.delete.question" data-cy="productDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-product-heading" v-text="$t('autoheavenApp.product.delete.question', { id: removeId })">
          Are you sure you want to delete this Product?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-product"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeProduct()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./product.component.ts"></script>
