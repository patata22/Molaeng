// const HOST = "http://localhost:8080/molaeng";
const HOST = "https://j7a604.p.ssafy.io/molaeng";

const USER = "/user";

export default {
  userInfo: {
    getUserInfo: () => HOST + USER, // post
    editProfile: () => HOST + USER, // update
    deleteUser: () => HOST + USER, // delete
  },
};
