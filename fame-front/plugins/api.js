import { get, post } from './http'

const api = {
  getArticles (page, limit) {
    let params = {
      page: page,
      limit: limit || 5
    }
    return get('/article', params)
  },
  getArticle (id) {
    return get('/article/' + id)
  },
  getCategories () {
    return get('/category')
  },
  getTags () {
    return get('/tag')
  },
  getDistance () {
    return get('/Distance')
  },
  getPage (title) {
    return get('/page/' + title)
  },
  getArchives () {
    return get('/archive')
  },
  getTrip () {
    return get('/trip')
  },
  getPeriod () {
    return get('/period')
  },
  getSoc () {
    return get('/soc')
  },
  getChrgDistance () {
    return get('/ChrgDistance')
  },
  getChrgDuration () {
    return get('/ChrgDuration')
  },
  getAcc () {
    return get('/accel')
  },
  getComment (articleId, page, limit) {
    let params = {
      articleId: articleId,
      page: page,
      limit: limit || 5
    }
    return get('comment', params)
  },
  postComment (articleId, pid, content, name, email, website) {
    let params = {
      articleId: articleId,
      pid: pid,
      content: content,
      name: name,
      email: email,
      website: website
    }
    return post('/comment', params)
  },
  assessComment (commentId, assess) {
    let params = {
      assess: assess
    }
    return post('/comment/' + commentId + '/assess', params)
  }
}

export default api
