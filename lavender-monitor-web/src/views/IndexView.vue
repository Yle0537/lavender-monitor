<template>
  <el-container class="main-container">
    <el-header class="main-header">
      <el-image src="/logo.png"
                style="height: 30px"/>
      <span style="margin-left: 4px; font-size: 16px; font-weight: bold">Lavender 运维监控系统</span>
      <div class="tabs">
        <tab-item v-for="item in tabs" :active="item.id === tab"
                  :name="item.name" @click="changePage(item)"/>
        <el-switch v-model="dark"
                   :active-action-icon="Moon" :inactive-action-icon="Sunny"
                   active-color="#424242"
                   style="margin: 0 20px"/>
        <el-dropdown>
          <div style="text-align: right;line-height: 16px;margin-right: 10px">
            <div>
              <el-tag v-if="store.isAdmin" size="small" type="success">管理员</el-tag>
              <el-tag v-else size="small">子账户</el-tag>
              {{ store.user.username }}
            </div>
            <div style="font-size: 13px;color: grey">{{ store.user.email }}</div>
          </div>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="userLogout">
                <el-icon>
                  <Back/>
                </el-icon>
                退出登录
              </el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </el-header>
    <el-main class="main-content">
      <router-view v-slot="{ Component }">
        <transition mode="out-in" name="el-fade-in-linear">
          <keep-alive exclude="Security">
            <component :is="Component"/>
          </keep-alive>
        </transition>
      </router-view>
    </el-main>
  </el-container>
</template>

<script setup>
import {logout} from '@/net'
import router from "@/router";
import {Back, Moon, Sunny} from "@element-plus/icons-vue";
import {ref} from "vue";
import {useDark} from "@vueuse/core";
import TabItem from "@/component/TabItem.vue";
import {useRoute} from "vue-router";
import {useStore} from "@/store";

const store = useStore()

const route = useRoute()
const dark = ref(useDark())
const tabs = [
  {id: 1, name: '管理', route: 'manage'},
  {id: 2, name: '安全', route: 'security'}
]
const defaultIndex = () => {
  for (let tab of tabs) {
    if (route.name === tab.route)
      return tab.id
  }
  return 1
}
const tab = ref(defaultIndex())

function changePage(item) {
  tab.value = item.id
  router.push({name: item.route})
}

function userLogout() {
  logout(() => router.push("/"))
}
</script>

<style scoped>
.main-container {
  height: 100vh;
  width: 100vw;

  .main-header {
    height: 55px;
    background-color: var(--el-bg-color);
    border-bottom: solid 1px var(--el-border-color);
    display: flex;
    align-items: center;

    .tabs {
      height: 55px;
      gap: 10px;
      flex: 1px;
      display: flex;
      align-items: center;
      justify-content: right;
    }
  }

  .main-content {
    height: 100%;
    background-color: #f5f5f5;
  }
}

.dark .main-container .main-content {
  background-color: #232323;
}
</style>
