import request from '@/utils/request'
// -------------- App升级名单管理 start
export function batchSaveUpgradeList(data) {
  return request({
    url: '/platform/upgrade/list/batchSave',
    method: 'POST',
    data
  })
}

export function batchDeleteUpgradeList(data) {
  return request({
    url: '/platform/upgrade/list/batch/delete',
    method: 'DELETE',
    data
  })
}

// -------------- App升级名单管理 end

// -------------- App版本信息管理 start
export function getAppInfoList(params) {
  return request({
    url: '/platform/app/info',
    method: 'GET',
    params
  })
}

export function addAppInfo(data) {
  return request({
    url: '/platform/app/info',
    method: 'POST',
    data
  })
}

export function updateAppInfo(data) {
  return request({
    url: '/platform/app/info',
    method: 'PUT',
    data
  })
}

export function deleteAppInfo(id) {
  return request({
    url: '/platform/app/info/' + id,
    method: 'DELETE'
  })
}
// -------------- App版本信息管理 end

// -------------- App版本信息管理 start
export function getAppVersionList(params) {
  return request({
    url: '/platform/app/versions',
    method: 'GET',
    params
  })
}

export function addAppVersion(data) {
  return request({
    url: '/platform/app/versions',
    method: 'POST',
    data
  })
}

export function updateAppVersions(data) {
  return request({
    url: '/platform/app/versions',
    method: 'PUT',
    data
  })
}

export function deleteAppVersion(id) {
  return request({
    url: '/platform/app/versions/' + id,
    method: 'DELETE'
  })
}
// -------------- App版本信息管理 end

// -------------- 机器信息管理 start
export function getInUpgradeList(params) {
  return request({
    url: '/platform/upgrade/list/in',
    method: 'GET',
    params
  })
}

export function getOutMachineInfoList(params) {
  return request({
    url: '/platform/machines/out',
    method: 'GET',
    params
  })
}

export function getMachineInfoList(params) {
  return request({
    url: '/platform/machines',
    method: 'GET',
    params
  })
}

export function addMachineInfo(data) {
  return request({
    url: '/platform/machines',
    method: 'POST',
    data
  })
}

export function updateMachineInfo(data) {
  return request({
    url: '/platform/machines',
    method: 'PUT',
    data
  })
}

export function deleteMachineInfo(id) {
  return request({
    url: '/platform/machines/' + id,
    method: 'DELETE'
  })
}
// -------------- 机器信息管理 end

// -------------- 型号管理 start
export function getMachineModelList(params) {
  return request({
    url: '/platform/models',
    method: 'GET',
    params
  })
}

export function addMachineModel(data) {
  return request({
    url: '/platform/models',
    method: 'POST',
    data
  })
}

export function updateMachineModel(data) {
  return request({
    url: '/platform/models',
    method: 'PUT',
    data
  })
}

export function deleteMachineModel(id) {
  return request({
    url: '/platform/models/' + id,
    method: 'DELETE'
  })
}
// -------------- 型号管理 end

export function getAppVersion(params) {
  return request({
    url: '/app/list',
    method: 'get',
    params
  })
}

export function getMachine(params) {
  return request({
    url: '/app/machine/list',
    method: 'get',
    params
  })
}

export function deleteBatchMachine(data) {
  return request({
    url: '/app/machine/deleteBatch',
    method: 'POST',
    data
  })
}

export function updateBatchMachine(data) {
  return request({
    url: '/app/machine/updateBatch',
    method: 'POST',
    data
  })
}

export function updateAppVersion(data) {
  return request({
    url: '/app/update',
    method: 'POST',
    data
  })
}

export function getAppUseLogs(params) {
  return request({
    url: '/use/app/log',
    method: 'get',
    params
  })
}
