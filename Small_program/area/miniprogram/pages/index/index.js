//index.js
const app = getApp()

Page({
  data: {
    motto: 'hello world',
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo')
  },

  bindViewTap: function(){
    wx.navigateTo({
      url: '../list/list',
    })
  },

  onLoad: function() {
    if (app.globalData.userInfo) {
      this.setData({
        userInfo:app.globalData.userInfo,
        hasUserInfo:true
      })
    } else if (this.data.canIUse){
      app.userInfoReadyCallback = res =>{
        this.setData({
          userInfo:res.userInfo,
          hasUserInfo:true
        })
      }
    }else{
      wx.getUserInfo({
        success: res=>{
          app.globalData.userInfo = res.userInfo
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
        }
      })
    }
  },
  getUserInfo: function (e) {
    console.log(e)
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  }
})
