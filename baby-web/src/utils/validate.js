/**
 * Created by jiachenpan on 16/11/18.
 */

export function isvalidUsername(str) {
  // const valid_map = ['admin', 'editor']
  // return valid_map.indexOf(str.trim()) >= 0
  return true
}

/* 合法uri*/
export function validateURL(textval) {
  const urlregex = /^(https?|ftp):\/\/([a-zA-Z0-9.-]+(:[a-zA-Z0-9.&%$-]+)*@)*((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]?)(\.(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])){3}|([a-zA-Z0-9-]+\.)*[a-zA-Z0-9-]+\.(com|edu|gov|int|mil|net|org|biz|arpa|info|name|pro|aero|coop|museum|[a-zA-Z]{2}))(:[0-9]+)*(\/($|[a-zA-Z0-9.,?'\\+&%$#=~_-]+))*$/
  return urlregex.test(textval)
}

/* 小写字母*/
export function validateLowerCase(str) {
  const reg = /^[a-z]+$/
  return reg.test(str)
}

/* 大写字母*/
export function validateUpperCase(str) {
  const reg = /^[A-Z]+$/
  return reg.test(str)
}

/* 大小写字母*/
export function validatAlphabets(str) {
  const reg = /^[A-Za-z]+$/
  return reg.test(str)
}

export function validateFileSuffix(suffix) {
  // [.](jpg|gif|bmg|txt)$
  const reg = /[.](xls|xlsx|ppt|pptx|doc|docx|txt|pdf|jpg|gif|jpeg|png|bmp|mp3|mp4)+$/
  return reg.test(suffix)
}

export function validateImgFile(file, nullable) {
  if (!nullable) {
    if (file === '') {
      return true
    }
  }
  const reg = /[.](jpg|gif|jpeg|png|bmp)+$/
  return reg.test(file)
}

export function validateVideoFile(file, nullable) {
  if (!nullable) {
    if (file === '') {
      return true
    }
  }
  const reg = /[.](mp4)+$/
  return reg.test(file)
}

export function validateAudioFile(file, nullable) {
  if (!nullable) {
    if (file === '') {
      return true
    }
  }
  const reg = /[.](m4a|mp3|wav)+$/
  return reg.test(file)
}

export function validateFile(file, nullable) {
  if (!nullable) {
    if (file === '') {
      return true
    }
  }
  const reg = /[.](xls|xlsx|ppt|pptx|doc|docx|txt|pdf|zip)+$/
  return reg.test(file)
}

export const validateImage = (rule, value, callback) => {
  if (rule.required && value === '') {
    callback(new Error(rule.notNullMessage))
    return false
  }
  if (!validateImgFile(value, rule.required)) {
    callback(new Error(rule.TypeErrorMessage))
    return false
  } else {
    callback()
  }
}

export const validateVideo = (rule, value, callback) => {
  if (rule.required && value === '') {
    callback(new Error(rule.notNullMessage))
    return false
  }
  if (!validateVideoFile(value, rule.required)) {
    callback(new Error(rule.TypeErrorMessage))
    return false
  } else {
    callback()
  }
}

export const validateAudio = (rule, value, callback) => {
  if (rule.required && value === '') {
    callback(new Error(rule.notNullMessage))
    return false
  }
  if (!validateAudioFile(value, rule.required)) {
    callback(new Error(rule.TypeErrorMessage))
    return false
  } else {
    callback()
  }
}
