FROM node:19-alpine as build-stage
WORKDIR /app
COPY ./package*.json ./
RUN npm install
COPY . .
RUN npm run build

# этап production (production-stage)
FROM nginx:1.24.0 as production-stage
COPY --from=build-stage /app/dist /usr/share/nginx/html
EXPOSE 80 8080
CMD ["nginx", "-g", "daemon off;"]
