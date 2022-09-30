import axios from "axios";

const baseURL = "https://j7a604.p.ssafy.io/molaeng";
// const baseURL = "http://localhost:8080/molaeng";

/**
 * 사용방법 예시
 *
 * import API from "@/api/APIs";
 *
 * const api = API;
 *
 * // response.data가 {"seoul": 6543, "my": 6345, "gugunName":'강남구' } 형식일 때
 *
 * api.outPrice(this.recipeId).then((res) => {
 *
 *     this.seoul = res.seoul;
 *
 *     this.my = res.my;
 *
 *     this.gugunName = res.gugunName;
 *
 *   });
 */
const API = {
  instance: axios.create({
    baseURL,
    headers: {
      "Content-Type": "application/json",
    },
  }),
  async outPrice(recipeId) {
    const response = await this.instance.get("/recipe/outeat/" + recipeId);
    return response.data;
  },
  async recipeLike(recipeId, userId) {
    const response = await this.instance.post("/recipe/like/", {
      recipeId: recipeId,
      userId: userId,
    });
    return response.data;
  },
};

export default API;
