<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="category">
        <h2 class="jh-entity-heading" data-cy="categoryDetailsHeading">
          <span v-text="$t('autoheavenApp.category.detail.title')">Category</span> {{ category.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('autoheavenApp.category.xmlCategoryId')">Xml Category Id</span>
          </dt>
          <dd>
            <span>{{ category.xmlCategoryId }}</span>
          </dd>
          <dt>
            <span v-text="$t('autoheavenApp.category.parentId')">Parent Id</span>
          </dt>
          <dd>
            <span>{{ category.parentId }}</span>
          </dd>
          <dt>
            <span v-text="$t('autoheavenApp.category.name')">Name</span>
          </dt>
          <dd>
            <span>{{ category.name }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="category.id" :to="{ name: 'CategoryEdit', params: { categoryId: category.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./category-details.component.ts"></script>
