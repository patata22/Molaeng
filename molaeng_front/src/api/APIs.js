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
    return response.data.result;
  },
  async dislikeRecipe(recipeId, userId) {
    const response = await this.instance.delete("/recipe/like", {
      data: {
        recipeId: recipeId,
        userId: userId,
      },
    });
    return response.data.result;
  },
  async getCalendar(userId, year, month) {
    const response = await this.instance.get(
      "/diary/calendar" +
        "?userId=" +
        userId +
        "&year=" +
        year +
        "&month=" +
        month
    );
    return response.data;
  },
  async getMonthGraph(userId, year, month) {
    const response = await this.instance.get(
      "/diary/month" + "?userId=" + userId + "&year=" + year + "&month=" + month
    );
    return response.data;
  },
  async getWeekGraph(userId, year, month) {
    const response = await this.instance.get(
      "/diary/week" + "?userId=" + userId + "&year=" + year + "&month=" + month
    );
    return response.data;
  },
  async saveDiary(userId, recipeId, saveCost) {
    const response = await this.instance.post("/diary", {
      userId: userId,
      recipeId: recipeId,
      saveCost: saveCost,
    });
    return response.data;
  },
  async getDiary(userId, date) {
    const response = await this.instance.get(
      "/diary" + "?userId=" + userId + "&date=" + date
    );
    return response.data;
  },
  async deleteDiary(diaryId) {
    const response = await this.instance.delete("/diary/" + diaryId);
    return response.data;
  },
  async recentRecipe(recipeIdList) {
    let queryParam = "";
    for (let recipeId of recipeIdList) {
      queryParam += recipeId + ",";
    }
    queryParam = queryParam.slice(0, -1);
    const response = await this.instance.get(
      "/recipe/history?recipeIdList=" + queryParam
    );
    return response.data.result;
  },
};

export default API;
