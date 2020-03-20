
var urlApi = Vue.resource('/url{/link}');

// var router = new VueRouter({
//     routes: [
//         { path: '/a', redirect: '/b' }
//     ]
// });


Vue.component('reply-string', {
    props: ['info'],
    data: function() {
        return {
            response: ''
        }
    },
    template: '<p style="color: darkblue; line-height: 0.3">{{ response }}</p>',
    watch: {
        info: function (newVal, oldVal) {
            this.response = newVal
        }
    }
});

Vue.component('long-to-short', {
    data:
        function() {
            return {
                text: '',
                response: '',
            }
        },
    template:
        '<div class="layer">' +
            '<p>Generate short link:</p>' +
            '<div><input type="text" placeholder="Paste your url here" v-model="text" /></div>' +
            '<div><input type="button" value="Generate" @click="generate" /></div>' +
            '<reply-string :info="response" />' +
        '</div>',
    methods: {
        generate: function () {
            var longUrl = {text: this.text};

            console.log(longUrl);
            urlApi.update({}, longUrl).then(result =>
                result.json().then(data => {
                    console.log(data.message);
                    this.response = data.message
                })
            );
            this.text = ''
        }
    }
});

Vue.component('short-to-long', {
    data:
        function() {
            return {
                text: '',
                response: '',
            }
        },
    template:
    '<div class="layer">' +
        '<p>Get full url by short link:</p>' +
        '<div><input type="text" placeholder="Paste your short url here" v-model="text" /></div>' +
        '<div><input type="button" value="Send" @click="getLong" /></div>' +
        '<reply-string :info="response" />' +
    '</div>',
    methods: {
        getLong: function () {
            var shortUrl = {text: this.text};
            console.log(shortUrl);
            urlApi.save({}, shortUrl).then(result =>
                result.json().then(data => {
                    console.log(data.message);
                    this.response = data.message;
                })
            );
            this.text = ''
        }
    }
});

Vue.component('message-row', {
    props: ['row'],
    template: '<div>{{ row.text }}</div>'
});

var shortener = new Vue({
    el: '#shortener',
    template:
        '<div>' +
            '<long-to-short />' +
            '<short-to-long />' +
        '</div>'
});