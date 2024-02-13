import request from '@/utils/request'

// 查询课程列表
export function listCourses(query) {
  return request({
    url: '/gym/course/queryAll',
    method: 'get',
    params: query
  })
}

// 查询课程详细信息
export function getCourse(courseId) {
  return request({
    url: '/gym/course/getDetail',
    method: 'get',
    params: {courseId}
  })
}

// 添加课程
export function addCourse(data) {
  return request({
    url: '/gym/course/add',
    method: 'post',
    data: data
  })
}

// 更新课程信息
export function updateCourse(data) {
  return request({
    url: '/gym/course/update',
    method: 'post',
    data: data
  })
}

// 删除课程
export function deleteCourse(courseId) {
  return request({
    url: '/gym/course/delete',
    method: 'get',
    params: {courseId}
  })
}

//获取所有课程评论
export function listCommentsById(courseId) {
  return request({
    url: '/gym/course/comment/listAll',
    method: 'get',
    params: {courseId}
  })
}

//添加课程评论
export function addComment(data) {
  return request({
    url: '/gym/course/comment/add',
    method: 'post',
    data: data
  })
}

//删除课程评论
export function getCourseComments(commentId) {
  return request({
    url: '/gym/course/comment/get',
    method: 'get',
    params: {commentId}
  })
}

//删除课程评论
export function deleteComment(commentId) {
  return request({
    url: '/gym/course/comment/delete',
    method: 'get',
    params: {commentId}
  })
}
