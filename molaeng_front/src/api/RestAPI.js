// const HOST = "http://localhost:8080/molaeng";
const HOST = "https://j7a604.p.ssafy.io/molaeng";

const USER = "/user";
const RECIPE = "/recipe";

export default {
  userInfo: {
    getUserInfo: () => HOST + USER, // post
    editProfile: () => HOST + USER, // update
    deleteUser: () => HOST + USER, // delete
  },
  recipeInfo: {
    getRecipeInfo: () => HOST + RECIPE,
    getRecipeDetail: () => HOST + RECIPE + "/detail",
    registRecipeLike: () => HOST + RECIPE + "/like",
    deleteRecipeLike: () => HOST + RECIPE + "/like",
  },
};
