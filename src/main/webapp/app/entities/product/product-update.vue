<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="autoheavenApp.product.home.createOrEditLabel"
          data-cy="ProductCreateUpdateHeading"
          v-text="$t('autoheavenApp.product.home.createOrEditLabel')"
        >
          Create or edit a Product
        </h2>
        <div>
          <div class="form-group" v-if="product.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="product.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('autoheavenApp.product.countryOfOrigin')" for="product-countryOfOrigin"
              >Country Of Origin</label
            >
            <input
              type="text"
              class="form-control"
              name="countryOfOrigin"
              id="product-countryOfOrigin"
              data-cy="countryOfOrigin"
              :class="{ valid: !$v.product.countryOfOrigin.$invalid, invalid: $v.product.countryOfOrigin.$invalid }"
              v-model="$v.product.countryOfOrigin.$model"
              required
            />
            <div v-if="$v.product.countryOfOrigin.$anyDirty && $v.product.countryOfOrigin.$invalid">
              <small class="form-text text-danger" v-if="!$v.product.countryOfOrigin.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.product.countryOfOrigin.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 50 })"
              >
                This field cannot be longer than 50 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('autoheavenApp.product.available')" for="product-available">Available</label>
            <input
              type="checkbox"
              class="form-check"
              name="available"
              id="product-available"
              data-cy="available"
              :class="{ valid: !$v.product.available.$invalid, invalid: $v.product.available.$invalid }"
              v-model="$v.product.available.$model"
              required
            />
            <div v-if="$v.product.available.$anyDirty && $v.product.available.$invalid">
              <small class="form-text text-danger" v-if="!$v.product.available.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('autoheavenApp.product.pictureUrls')" for="product-pictureUrls"
              >Picture Urls</label
            >
            <input
              type="text"
              class="form-control"
              name="pictureUrls"
              id="product-pictureUrls"
              data-cy="pictureUrls"
              :class="{ valid: !$v.product.pictureUrls.$invalid, invalid: $v.product.pictureUrls.$invalid }"
              v-model="$v.product.pictureUrls.$model"
              required
            />
            <div v-if="$v.product.pictureUrls.$anyDirty && $v.product.pictureUrls.$invalid">
              <small class="form-text text-danger" v-if="!$v.product.pictureUrls.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.product.pictureUrls.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 255 })"
              >
                This field cannot be longer than 255 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('autoheavenApp.product.name')" for="product-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="product-name"
              data-cy="name"
              :class="{ valid: !$v.product.name.$invalid, invalid: $v.product.name.$invalid }"
              v-model="$v.product.name.$model"
              required
            />
            <div v-if="$v.product.name.$anyDirty && $v.product.name.$invalid">
              <small class="form-text text-danger" v-if="!$v.product.name.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.product.name.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 50 })"
              >
                This field cannot be longer than 50 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('autoheavenApp.product.vendor')" for="product-vendor">Vendor</label>
            <input
              type="text"
              class="form-control"
              name="vendor"
              id="product-vendor"
              data-cy="vendor"
              :class="{ valid: !$v.product.vendor.$invalid, invalid: $v.product.vendor.$invalid }"
              v-model="$v.product.vendor.$model"
            />
            <div v-if="$v.product.vendor.$anyDirty && $v.product.vendor.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.product.vendor.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 50 })"
              >
                This field cannot be longer than 50 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('autoheavenApp.product.vendorCode')" for="product-vendorCode">Vendor Code</label>
            <input
              type="text"
              class="form-control"
              name="vendorCode"
              id="product-vendorCode"
              data-cy="vendorCode"
              :class="{ valid: !$v.product.vendorCode.$invalid, invalid: $v.product.vendorCode.$invalid }"
              v-model="$v.product.vendorCode.$model"
            />
            <div v-if="$v.product.vendorCode.$anyDirty && $v.product.vendorCode.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.product.vendorCode.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 50 })"
              >
                This field cannot be longer than 50 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('autoheavenApp.product.properties')" for="product-properties">Properties</label>
            <div>
              <div v-if="product.properties" class="form-text text-danger clearfix">
                <a
                  class="pull-left"
                  v-on:click="openFile(product.propertiesContentType, product.properties)"
                  v-text="$t('entity.action.open')"
                  >open</a
                ><br />
                <span class="pull-left">{{ product.propertiesContentType }}, {{ byteSize(product.properties) }}</span>
                <button
                  type="button"
                  v-on:click="
                    product.properties = null;
                    product.propertiesContentType = null;
                  "
                  class="btn btn-secondary btn-xs pull-right"
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                </button>
              </div>
              <input
                type="file"
                ref="file_properties"
                id="file_properties"
                data-cy="properties"
                v-on:change="setFileData($event, product, 'properties', false)"
                v-text="$t('entity.action.addblob')"
              />
            </div>
            <input
              type="hidden"
              class="form-control"
              name="properties"
              id="product-properties"
              data-cy="properties"
              :class="{ valid: !$v.product.properties.$invalid, invalid: $v.product.properties.$invalid }"
              v-model="$v.product.properties.$model"
              required
            />
            <input
              type="hidden"
              class="form-control"
              name="propertiesContentType"
              id="product-propertiesContentType"
              v-model="product.propertiesContentType"
            />
            <div v-if="$v.product.properties.$anyDirty && $v.product.properties.$invalid">
              <small class="form-text text-danger" v-if="!$v.product.properties.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('autoheavenApp.product.price')" for="product-price">Price</label>
            <input
              type="number"
              class="form-control"
              name="price"
              id="product-price"
              data-cy="price"
              :class="{ valid: !$v.product.price.$invalid, invalid: $v.product.price.$invalid }"
              v-model.number="$v.product.price.$model"
              required
            />
            <div v-if="$v.product.price.$anyDirty && $v.product.price.$invalid">
              <small class="form-text text-danger" v-if="!$v.product.price.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.product.price.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('autoheavenApp.product.totalRating')" for="product-totalRating"
              >Total Rating</label
            >
            <input
              type="number"
              class="form-control"
              name="totalRating"
              id="product-totalRating"
              data-cy="totalRating"
              :class="{ valid: !$v.product.totalRating.$invalid, invalid: $v.product.totalRating.$invalid }"
              v-model.number="$v.product.totalRating.$model"
              required
            />
            <div v-if="$v.product.totalRating.$anyDirty && $v.product.totalRating.$invalid">
              <small class="form-text text-danger" v-if="!$v.product.totalRating.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.product.totalRating.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('autoheavenApp.product.description')" for="product-description">Description</label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="product-description"
              data-cy="description"
              :class="{ valid: !$v.product.description.$invalid, invalid: $v.product.description.$invalid }"
              v-model="$v.product.description.$model"
            />
            <div v-if="$v.product.description.$anyDirty && $v.product.description.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.product.description.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 256 })"
              >
                This field cannot be longer than 256 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('autoheavenApp.product.order')" for="product-order">Order</label>
            <select class="form-control" id="product-order" data-cy="order" name="order" v-model="product.order">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="product.order && orderOption.id === product.order.id ? product.order : orderOption"
                v-for="orderOption in orders"
                :key="orderOption.id"
              >
                {{ orderOption.id }}
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
            :disabled="$v.product.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./product-update.component.ts"></script>
